lynx -dump $1 > oldsite.txt

while [ true ]
do
	lynx -dump $1 > site.txt
	if [ "`git diff site.txt oldsite.txt`" != "" ]
	then
		git diff site.txt oldsite.txt > diff.txt
		cat diff.txt
		
	fi
	cat site.txt > oldsite.txt
	sleep $2
done
