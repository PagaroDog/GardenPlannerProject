import csv
from urllib.request import urlopen
from urllib.request import Request
from bs4 import BeautifulSoup
from os.path import basename
import os
import requests
import sys


def downloadImagesFromName(name):
    try:
        folder = 'Resources/imgs/' + name
        if not os.path.exists(folder):
            # query the website and return the html to the variable ‘page’

            req = Request('https://commons.wikimedia.org/wiki/' + name, headers = {'User-Agent': 'Mozilla/5.0'})

            page = urlopen(req)
        
            # parse the html using beautiful soup and store in variable `soup`
            soup = BeautifulSoup(page, 'html.parser')
            images = soup.find_all('img')
            i = 0
            print('trying to create ' + name)
            for image in images:
                try:
                    link = image['src']
                    authorLink = 'https://commons.wikimedia.org' + image.parent['href']
                    if (authorLink.find('www.') != -1):
                        raise()
                    authorReq = Request(authorLink, headers = {'User-Agent': 'Mozilla/5.0'})
                    authorPage = urlopen(authorReq)
                    soup = BeautifulSoup(authorPage, 'html.parser')
                    print(link)
                    directory = folder + '/' + str(i)
                    if not os.path.exists(directory):
                        os.makedirs(directory)
                    try:
                        print(soup.find('td', id='fileinfotpl_aut').next_sibling.next_sibling.text)
                        with open(directory + '/' + 'author.txt', 'w') as f:
                            f.write(soup.find('td', id='fileinfotpl_aut').next_sibling.next_sibling.text + '\n')
                            f.write(authorLink)
                    except:
                        with open(directory + '/' + 'author.txt', 'w') as f:
                            f.write("\nauthor missing")
                            f.write("\n" + authorLink)
                    with open(directory + '/' + basename(link), 'wb') as f:
                        f.write(requests.get(link).content)
                    print('success')
                    i += 1
                except:
                    print(sys.exc_info()[0])
    except:
        print(name + " failed")

csvfile = open('plantInfo.csv', 'r', newline = '')
reader = csv.reader(csvfile)
for row in reader:
    name = row[0]
    name = name.replace(' ', '_')
    downloadImagesFromName(name)