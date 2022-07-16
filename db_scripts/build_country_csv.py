# This script will load countries and create a data.csv file that can be
# imported into database instance .db

import csv
import json
import urllib.request


def map_item(item):
    return [
        item['id'],
        item['name'],
        item['iso3'],
        item['iso2'],
        item['capital'],
        item['region'],
        item['subregion'],
        item['emoji'],
        item['emojiU'],
    ]


if __name__ == '__main__':
    COUNTRY_URL = 'https://raw.githubusercontent.com/dr5hn/countries-states-cities-database/master/countries.json'
    webURL = urllib.request.urlopen(COUNTRY_URL)
    content = webURL.read()
    encoding = webURL.info().get_content_charset('utf-8')

    data = json.loads(content.decode(encoding))
    data_list = list(data)
    mapped_list = list(map(map_item, data_list))

    with open('data.csv', 'w', encoding='utf-8', newline='') as ff:
        write = csv.writer(ff)
        write.writerow(('id', 'name', 'iso3', 'iso2', 'capital', 'region', 'subregion', 'emoji', 'emojiU'))
        write.writerows(mapped_list)
