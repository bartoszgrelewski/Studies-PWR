u=`curl -s https://api.thecatapi.com/v1/images/search | jq -r '.[0].url'`

wget -q $u

u=`echo $u | cut -d '/' -f 5`
catimg ./$u

echo `curl -s http://api.icndb.com/jokes/random | jq '.value.joke'`
