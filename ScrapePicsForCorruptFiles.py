import csv
from urllib.request import urlopen
from urllib.request import Request
from bs4 import BeautifulSoup
from os.path import basename
import os
import requests
import sys
from PIL import Image


def downloadImagesFromName(name):
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
                try:
                    # query the website and return the html to the variable ‘page’
                    linkFile = open(folder + '/author.txt', 'r')
                    lines = linkFile.readlines()

                    req = Request(lines[2], headers = {'User-Agent': 'Mozilla/5.0'})

                    page = urlopen(req)
                
                    # parse the html using beautiful soup and store in variable `soup`
                    soup = BeautifulSoup(page, 'html.parser')
                    images = soup.find_all('img')
                    files = 0
                    for image in images:
                        if 'File:' in str(image):
                            try:
                                link = image['src']
                                print(link)
                                for pic in picFiles:
                                        os.remove(folder + '/' + str(pic))
                                        with open(folder + '/' + basename(link), 'wb') as f:
                                            f.write(requests.get(link).content)
                                        print('success')
                            except:
                                print(sys.exc_info()[0])
                except:
                    print(name + " failed")
        folderNum += 1
        folder = 'Resources/imgs/' + name + "/" + str(folderNum)


csvfile = open('plantInfo.csv', 'r', newline = '')
reader = csv.reader(csvfile)
for row in reader:
    name = row[0]
    name = name.replace(' ', '_')
    downloadImagesFromName(name)