import csv
from urllib.request import urlopen
from urllib.request import Request
from bs4 import BeautifulSoup
import re

def getURLFromName(name):
    try:
        # query the website and return the html to the variable ‘page’

        req = Request('https://garden.org/plants/search/text/?q=' + name.replace(' ', '+'), headers = {'User-Agent': 'Mozilla/5.0'})

        page = urlopen(req)
    
        # parse the html using beautiful soup and store in variable `soup`
        soup = BeautifulSoup(page, 'html.parser')
        return 'https://garden.org' + soup.find('i', string=name).parent['href']
    except:
        return 'fail'

def plantFromURL(url, csvMode):
    try:
        # query the website and return the html to the variable ‘page’

        req = Request(url, headers = {'User-Agent': 'Mozilla/5.0'})

        page = urlopen(req)
    
        # parse the html using beautiful soup and store in variable `soup`
        soup = BeautifulSoup(page, 'html.parser')
        try:
            sun = str(list(soup.find('td', string = 'Sun Requirements:').next_siblings))
            light = ''
            lightConditions = ['Full Sun', 'Full Sun to Partial Shade', 'Partial or Dappled Shade', 'Partial Shade to Full Shade', 'Full Shade']
            for cond in lightConditions:
                if cond in sun:
                    light += cond + ', '
            light = light[:len(light)-2]
        except:
            light = 'fail'
        print(light)

        try:
            soil = str(list(soup.find('td', string = 'Water Preferences:').next_siblings))
            water = ''
            waterConditions = ['Wet', 'Wet Mesic', 'Mesic', 'Dry Mesic', 'Dry']
            for cond in waterConditions:
                if cond in soil:
                    water += cond + ', '
            water = water[:len(water)-2]
        except:
            water = 'fail'
        print(water)

        try:
            hSize = str(list(soup.find('b', string = 'Plant Spread').parent.parent.parent))
            print(hSize)
            height = re.sub('<[^>]+>|,|\([^\)]+\)', '', hSize)
            if 'feet' in height or 'ft' in height:
                scale = ' feet'
                height = height.split('feet', 1)[0]
                height = height.split('ft', 1)[0]
            elif 'cm' in height:
                scale = ' cm'
                height = height.split('cm', 1)[0]
            else:
                scale = ' inches'
                height = height.split('inches', 1)[0]
            numOnly = re.compile(r'[^\d-]+')
            height = numOnly.sub('', height.replace('to', '-')) + scale
        except:
            height = 'fail'
        print(height)
            

        csvfile = open('plantLightAndWater.csv', csvMode, newline='')
        writer = csv.writer(csvfile)
        writer.writerow([light, water, height])
    except:
        csvfile = open('plantLightAndWater.csv', csvMode, newline='')
        writer = csv.writer(csvfile)
        writer.writerow(['fail', 'fail', 'fail'])


csvfile = open('plantInfo.csv', 'r', newline='')
reader = csv.reader(csvfile)
reader = list(reader)

print(getURLFromName(reader[0][0]))
plantFromURL(getURLFromName(reader[0][0]), 'w')

for i in range(1, len(reader)):
    print(getURLFromName(reader[i][0]))
    plantFromURL(getURLFromName(reader[i][0]), 'a+')