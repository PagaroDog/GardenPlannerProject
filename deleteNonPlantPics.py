import csv
from urllib.request import urlopen
from urllib.request import Request
from bs4 import BeautifulSoup
from os.path import basename
import os
import requests
import sys
from PIL import Image
import shutil

def checkImagesFromName(name):
    folderNum = 0
    folder = 'Resources/imgs/' + name + "/" + str(folderNum)
    while (os.path.exists(folder)):
        picFiles = [f for f in os.listdir(folder) if '.txt' not in str(f)]
        for pic in picFiles:
            if 'Wikispecies' in str(pic):
                shutil.rmtree(folder)

        folderNum += 1
        folder = 'Resources/imgs/' + name + "/" + str(folderNum)


csvfile = open('plantInfo.csv', 'r', newline = '')
reader = csv.reader(csvfile)

badNum = 0
for row in reader:
    name = row[0]
    name = name.replace(' ', '_')
    checkImagesFromName(name)
