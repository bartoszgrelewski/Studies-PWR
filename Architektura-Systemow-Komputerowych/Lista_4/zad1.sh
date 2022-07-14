#!/bin/bash

#INTEGERS USED BY CHART
avg="0"
o="0"

h1=0
h2=0
h3=0
h4=0
h5=0
h6=0
h7=0
h8=0
h9=0
h10=0

while [ true ]
do
	clear
	
	#HOW LONG SYS HAS BEEN UP
	t=`cat /proc/uptime | cut -d ' ' -f 1`
	t=${t/.*}
	echo "Time the system has been up: " $(($t/86400)) "days" $((($t%86400)/3600)) 		"hours" $(((($t%86400)%3600)/60)) "minutes" $(((($t%86400)%3600)%60)) "seconds"

	echo "------"

	#BATTERY INFO
	echo "BATTERY"
	cat /sys/class/power_supply/BAT0/uevent | grep CAPACITY=;

	echo "------"
	
	#LOADAVG 1,2,3
	load1=`cat /proc/loadavg | cut -d ' ' -f 1`
	load5=`cat /proc/loadavg | cut -d ' ' -f 2`
	load15=`cat /proc/loadavg | cut -d ' ' -f 3`

	echo "last minute:" $load1
	echo "last 5 minutes:" $load5
	echo "last 15 minutes:" $load15

	#CHART TO LOADAVG
	
	#INTEGERS
	h10=$h9
	h9=$h8
	h8=$h7
	h7=$h6
	h6=$h5
	h5=$h4
	h4=$h3
	h3=$h2
	h2=$h1
	
	#CUTTING VALUES FROM LOAD1 WHERE IS LAST MINUTE AVG LOAD 
	h1=`echo $load1 | cut -d '.' -f 2`

	max=$h10

	if [ $h9 -gt $max ]
	then
		max=$h9
	fi
	if [ $h8 -gt $max ]
	then
		max=$h8
	fi
	if [ $h7 -gt $max ]
	then
		max=$h7
	fi
	if [ $h6 -gt $max ]
	then
		max=$h6
	fi
	if [ $h5 -gt $max ]
	then
		max=$h5
	fi
	if [ $h4 -gt $max ]
	then
		max=$h4
	fi
	if [ $h3 -gt $max ]
	then
		max=$h3
	fi
	if [ $h2 -gt $max ]
	then
		max=$h2
	fi
	if [ $h1 -gt $max ]
	then
		max=$h1
	fi

	echo "0                                                 0.$max"
	#setab --> set background
	tput setab 3
	#setaf --> set foreground
	tput setaf 3
	chart=""
	if [ $max != 0 ]
	then
		#number = dlugosc jednej linijki wykresu dla danego i
		number=$((($h1*50)/$max))
		for ((i=0; i<=$number; i++))
		do
		#chart = string o dlugosci -
		chart=$chart-
		done
	else
		chart=-
	fi
	echo $chart
	chart=""
	if [ $max != 0 ]
	then
		number=$((($h2*50)/$max))
		for ((i=0; i<=$number; i++))
		do
		chart=$chart-
		done
	else
		chart=-
	fi
	echo $chart
	chart=""
	if [ $max != 0 ]
	then
		number=$((($h3*50)/$max))
		for ((i=0; i<=$number; i++))
		do
		chart=$chart-
		done
	else
		chart=-
	fi
	echo $chart
	chart=""
	if [ $max != 0 ]
	then
		number=$((($h4*50)/$max))
		for ((i=0; i<=$number; i++))
		do
		chart=$chart-
		done
	else
		chart=-
	fi
	echo $chart
	chart=""
	if [ $max != 0 ]
	then
		number=$((($h5*50)/$max))
		for ((i=0; i<=$number; i++))
		do
		chart=$chart-
		done
	else
		chart=-
	fi
	echo $chart
	chart=""
	if [ $max != 0 ]
	then
		number=$((($h6*50)/$max))
		for ((i=0; i<=$number; i++))
		do
		chart=$chart-
		done
	else
		chart=-
	fi
	echo $chart
	chart=""
	if [ $max != 0 ]
	then
		number=$((($h7*50)/$max))
		for ((i=0; i<=$number; i++))
		do
		chart=$chart-
		done
	else
		chart=-
	fi
	echo $chart
	chart=""
	if [ $max != 0 ]
	then
		number=$((($h8*50)/$max))
		for ((i=0; i<=$number; i++))
		do
		chart=$chart-
		done
	else
		chart=-
	fi
	echo $chart
	chart=""
	if [ $max != 0 ]
	then
		number=$((($h9*50)/$max))
		for ((i=0; i<=$number; i++))
		do
		chart=$chart-
		done
	else
		chart=-
	fi
	echo $chart
	chart=""
	if [ $max != 0 ]
	then
		number=$((($h10*50)/$max))
		for ((i=0; i<=$number; i++))
		do
		chart=$chart-
		done
	else
		chart=-
	fi
	echo $chart
	#BOLD ATTRIBUTE
	tput sgr0

	sleep 1
done
