from lxml import etree
from bs4 import BeautifulSoup
import requests
import pymysql

if __name__ == '__main__':
    html_data = requests.get("https://www.indiehackers.com/").content
    soup = BeautifulSoup(html_data, "lxml")
    firstColumn = soup.findAll("div", class_="story homepage-post ember-view normal")

    db = pymysql.connect(host='127.0.0.1',
                         user='root',
                         password='root',
                         database='le-project')
    insert_sql = "insert into blog(`title`, `digest`, `content`, `praise`, `status`,create_time,update_time) value (%s,%s,%s,%s,%s,now(),now()) " \
                 "ON DUPLICATE KEY UPDATE content = %s,praise=%s,update_time=now() "
    cursor = db.cursor()
    for row in firstColumn:
        title = row.find('h3').text
        detail_url = "https://www.indiehackers.com"+row.find_all('a')[0]['href']
        like_num = int(row.select("[class~=story__count-number]")[0].text)
        comment_num = int(row.select("[class~=story__count-number]")[1].text)

        params = []

        params.append(title)
        params.append(title)
        params.append(title)
        params.append(like_num)
        params.append(0)
        params.append(title)
        params.append(like_num)
        cursor.execute(insert_sql, params)
        db.commit()