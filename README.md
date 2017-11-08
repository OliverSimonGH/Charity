**Charity Webpage**

Navigation to the following URL: http://localhost:8080/charity/1

This webpage will display the total amount that has been donated to
the charity and the 10 most recent activities, these will display for the charity
with an ID of 1 as seen in the URL, if you want to see information for another charity
please change the ID in the URL (1-9).

The amount donated to the charity will appear on the left side of the webpage,
these totals appear in pounds and not pence;
The donation total will equal: 609668
The donated total will equal: 595809
The gift aid total will equal: 13859
(if the database is left untouched)

The 10 most recent activities of the charity will appear on the right side on the webpage,
within these events are donations and sponsor forms and they will be sorted by recent date;
The most recent activity is a sponsor form created by Brenna Ferroni at 2017-09-18 (YYYY/MM/DD)
The second most recent would be a donation of £2775 made by Ara Dorro at 2017-09-17
(if the database is left untouched)

**Charity API**

Use the following API to find all the charities associated with this website.
http://localhost:8080/api/charities

Use the following API to find a charity by name of your choice.
http://localhost:8080/api/charity?name={name}

Example One:
http://localhost:8080/api/charity?name=nspcc

Example Two:
You can also find multiple charities by name by adding another URL paramater
http://localhost:8080/api/charity?name=nspcc&name=oxfam

Use the following API to find a charity with a specfic ID
http://localhost:8080/api/charity/{ID}

Example One:
http://localhost:8080/api/charity/1

Use the following API to find a specific charities donations
http://localhost:8080/api/charity/{ID}/donations

Example One:
http://localhost:8080/api/charity/1/donations

Use the following API to find a specific charities donations total
http://localhost:8080/api/charity/{ID}/donations/total

Example One:
http://localhost:8080/api/charity/1/donations/total

Use the following API to find a specific charities sponsors
http://localhost:8080/api/charity/{ID}/sponsors

Example One:
http://localhost:8080/api/charity/1/sponsors

Use the following API to find a specific charities top ten recent events
http://localhost:8080/api/charity/{ID}/events

Example One:
http://localhost:8080/api/charity/1/events










