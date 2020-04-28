import csv
from urllib.request import urlopen
from urllib.request import Request
from bs4 import BeautifulSoup

def plantListFromURL(url, csvMode):
    # query the website and return the html to the variable ‘page’

    req = Request(url, headers = {'User-Agent': 'Mozilla/5.0'})

    page = urlopen(req)
  
    # parse the html using beautiful soup and store in variable `soup`
    soup = BeautifulSoup(page, 'html.parser')

    plants = soup.find('table').find('tr').next_siblings

    csvfile = open('plants.csv', csvMode, newline='')
    writer = csv.writer(csvfile)

    for plant in plants:
        if plant.find('a'):
            writer.writerow([plant.find('a')['href']])

plantListFromURL("https://www.wildflower.org/collections/collection.php?start=0&collection=DE&pagecount=100", 'w')
plantListFromURL("https://www.wildflower.org/collections/collection.php?start=100&collection=DE&pagecount=100", 'a+')

