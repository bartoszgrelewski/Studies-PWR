for file in /proc/[0-9]*
do
	n=`cat $file/status | cut -d ':' -f 1`
	d=`cat $file/status | cut -d ':' -f 2`

	echo `echo $n | cut -d ' ' -f 1` ":"  `echo $d | cut -d ' ' -f 1`
	echo `echo $n | cut -d ' ' -f 3` ":"  `echo $d | cut -d ' ' -f 3,4`
	echo `echo $n | cut -d ' ' -f 6` ":"  `echo $d | cut -d ' ' -f 7`
	echo `echo $n | cut -d ' ' -f 7` ":"  `echo $d | cut -d ' ' -f 8`
	echo "Opened files:" `sudo ls $file/fd | wc -l`
	echo -----------------
done


