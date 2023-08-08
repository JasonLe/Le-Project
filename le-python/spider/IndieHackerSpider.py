import pymysql
import requests
from bs4 import BeautifulSoup


def getSoup(url):
    html_data = requests.get(url).content
    soup = BeautifulSoup(html_data, "lxml")
    return soup


if __name__ == '__main__':

    db = pymysql.connect(host='127.0.0.1',
                         user='root',
                         password='root',
                         database='le-project')

    soup = getSoup("https://www.indiehackers.com/")
    column_list = ["story homepage-post ember-view normal", "story homepage-post ember-view"]
    # column_list = ["story homepage-post ember-view"]

    for index in range(len(column_list)):

        firstColumn = soup.findAll("div", class_=column_list[index])

        insert_sql = "insert into blog(`title`, `digest`, `content`,`image`, `praise`,`type`, `status`,create_time,update_time) value (%s,%s,%s,%s,%s,%s,%s,now(),now()) " \
                     "ON DUPLICATE KEY UPDATE content = %s,praise=%s,update_time=now() "
        cursor = db.cursor()
        for row in firstColumn:
            params = []

            # title = row.find('h3').text
            detail_url = "https://www.indiehackers.com" + row.find_all('a')[0]['href']
            # like_num = int(row.select("[class~=story__count-number]")[0].text)
            # comment_num = int(row.select("[class~=story__count-number]")[1].text)

            if index == 1:
                image = row.find_all('img')[0]['src']
                if "http" not in image:
                    image = ""
            else:
                image = ""

            detail_soup = getSoup(detail_url)
            detail_soup.find()

            title = detail_soup.find("h1", "post-page__title").text.strip()
            content_list = detail_soup.find("div", "post-page__body content ember-view")

            if content_list == None:
                continue
            content_list = content_list.contents
            content = ''.join('%s' % item for item in (content_list)).strip()

            like_num = detail_soup.find("div", "post-liker__count").text.strip()
            author = detail_soup.find("a", "ember-view post-page__byline").text.strip()

            params.append(title)
            params.append(title)
            params.append(content)
            params.append(image)
            params.append(like_num)
            params.append(index)
            params.append(0)
            params.append(content)
            params.append(like_num)
            cursor.execute(insert_sql, params)
            db.commit()
            print("下载完成：" + title)
