import csv
from urllib.request import urlopen
from urllib.request import Request
from bs4 import BeautifulSoup
from os.path import basename
import os
import requests
import sys
from PIL import Image

def checkImagesFromName(name, badNum):
    folderNum = 0
    folder = 'Resources/imgs/' + name + "/" + str(folderNum)
    while (os.path.exists(folder)):
        picFiles = [f for f in os.listdir(folder) if '.txt' not in str(f)]
        for pic in picFiles:
            try:
                img = Image.open(folder + '/' + str(pic)) # open the image file
                img.verify() # verify that it is, in fact an image
            except (IOError, SyntaxError) as e:
                print('Bad file:', str(pic)) # print out the names of corrupt files
                badNum += 1

        folderNum += 1
        folder = 'Resources/imgs/' + name + "/" + str(folderNum)
    return badNum


csvfile = open('plantInfo.csv', 'r', newline = '')
reader = csv.reader(csvfile)
badNum = 0
for row in reader:
    name = row[0]
    name = name.replace(' ', '_')
    badNum = checkImagesFromName(name, badNum)

print(badNum)