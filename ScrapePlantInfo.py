import csv
from urllib.request import urlopen
from urllib.request import Request
from bs4 import BeautifulSoup

def plantInfoFromURL(url, csvMode, errors):
    # query the website and return the html to the variable ‘page’

    req = Request(url, headers = {'User-Agent': 'Mozilla/5.0'})

    page = urlopen(req)
  
    # parse the html using beautiful soup and store in variable `soup`
    soup = BeautifulSoup(page, 'html.parser')

    main = soup.find('main')
    scienceName = main.find('h2', attrs={'class' : 'tax_sn'})
    scienceNameText = scienceName.text
    commonNames = list(scienceName.next_siblings)[3].text

    try:
        characteristics = main.find('h4', string = 'Plant Characteristics').parent
        try:
            duration = characteristics.find('strong', string = 'Duration:').find_next_sibling('a').text
        except:
            duration = 'duration needed'
            errors += 1
        try:
            habit = characteristics.find('strong', string = 'Habit:').find_next_sibling('a').text
        except:
            habit = 'habit needed'
            errors += 1
        try:
            fruit = characteristics.find('strong', string = 'Fruit:').next_sibling
        except:
            fruit = ''
        try:
            size = characteristics.find('strong', string = 'Size Class:').next_sibling
        except:
            size = 'size needed'
            errors += 1
    except:
        duration = 'duration needed'
        habit = ''
        leafShape = ''
        fruit = ''
        size = 'size needed'
        errors += 2


    try:
        bloom = main.find('h4', string = 'Bloom Information').parent
        try:
            bloomColor = bloom.find('strong', string = 'Bloom Color:').next_sibling
        except:
            bloomColor = 'color needed'
            errors += 1
        try:
            bloomTime = bloom.find('strong', string = 'Bloom Time:').next_sibling
        except:
            bloomTime = 'bloom needed'
            errors += 1
    except:
        bloomColor = 'color needed'
        bloomTime = 'bloom needed'
        errors += 2

    try:
        conditions = main.find('h4', string = 'Growing Conditions').parent
        try:
            water = conditions.find('strong', string = 'Water Use:').next_sibling
        except:
            water = 'water needed'
        try:
            light = conditions.find('strong', string = 'Light Requirement:').next_sibling
        except:
            light = 'light needed'
            errors += 1
        try:
            soilMoisture = conditions.find('strong', string = 'Soil Moisture:').next_sibling
        except:
            soilMoisture = 'moisture needed'

        if water == 'water needed':
            if soilMoisture != 'moisture needed':
                if soilMoisture == 'Dry':
                    water = 'Low, Medium'
                elif soilMoisture == 'Wet':
                    water = 'Medium, High'
                else:
                    water = 'Low, Medium, High'
            else:
                errors += 1
    except:
        water = 'water needed'
        light = 'light needed'
        soilMoisture = 'moisture needed'
        errors += 2


    csvfile = open('plantInfo.csv', csvMode, newline='')
    writer = csv.writer(csvfile)

    writer.writerow([scienceNameText, commonNames, duration, habit, fruit, size, bloomColor, bloomTime, water, light, soilMoisture])

    return errors

csvread = open('plants.csv', newline = '')
reader = csv.reader(csvread)
reader = list(reader)

bigErrors = 0
bigErrors = plantInfoFromURL('https://www.wildflower.org/plants/result.php?id_plant=ACMI2', 'w', bigErrors)

for i in range(1, len(reader)):
    print('https://www.wildflower.org' + reader[i][0].replace('..', ''))
    bigErrors = plantInfoFromURL('https://www.wildflower.org' + reader[i][0].replace('..', ''), 'a+', bigErrors)

print(bigErrors)