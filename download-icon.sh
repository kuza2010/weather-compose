#!/bin/bash

arrayDay=(
    https://openweathermap.org/img/wn/01d@4x.png
    https://openweathermap.org/img/wn/02d@4x.png
    https://openweathermap.org/img/wn/03d@4x.png
    https://openweathermap.org/img/wn/04d@4x.png
    https://openweathermap.org/img/wn/09d@4x.png
    https://openweathermap.org/img/wn/10d@4x.png
    https://openweathermap.org/img/wn/11d@4x.png
    https://openweathermap.org/img/wn/13d@4x.png
    https://openweathermap.org/img/wn/50d@4x.png
)
arrayDayNames=(
    app/src/main/res/drawable/ic_01d.png
    app/src/main/res/drawable/ic_02d.png
    app/src/main/res/drawable/ic_03d.png
    app/src/main/res/drawable/ic_04d.png
    app/src/main/res/drawable/ic_09d.png
    app/src/main/res/drawable/ic_10d.png
    app/src/main/res/drawable/ic_11d.png
    app/src/main/res/drawable/ic_13d.png
    app/src/main/res/drawable/ic_50d.png
)

arrayNight=(
    https://openweathermap.org/img/wn/01n@4x.png
    https://openweathermap.org/img/wn/02n@4x.png
    https://openweathermap.org/img/wn/03n@4x.png
    https://openweathermap.org/img/wn/04n@4x.png
    https://openweathermap.org/img/wn/09n@4x.png
    https://openweathermap.org/img/wn/10n@4x.png
    https://openweathermap.org/img/wn/11n@4x.png
    https://openweathermap.org/img/wn/13n@4x.png
    https://openweathermap.org/img/wn/50n@4x.png
)
arrayNightNames=(
    app/src/main/res/drawable/ic_01n.png
    app/src/main/res/drawable/ic_02n.png
    app/src/main/res/drawable/ic_03n.png
    app/src/main/res/drawable/ic_04n.png
    app/src/main/res/drawable/ic_09n.png
    app/src/main/res/drawable/ic_10n.png
    app/src/main/res/drawable/ic_11n.png
    app/src/main/res/drawable/ic_13n.png
    app/src/main/res/drawable/ic_50n.png
)


for index in ${!arrayDay[*]}
do
    curl ${arrayDay[$index]} --output ${arrayDayNames[$index]}
    curl ${arrayNight[$index]} --output ${arrayNightNames[$index]}
done