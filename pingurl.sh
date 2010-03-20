#!/bin/sh

a=50

while [ $a -gt 0 ]
do
  curl http://simplescala.latest.scala-spinup-check.appspot.com/ > /dev/null
  echo 'ping simple scala end.'
  curl http://slim3scala.latest.scala-spinup-check.appspot.com/ > /dev/null
  echo 'ping slim3 scala end.'
  curl http://listscala.latest.scala-spinup-check.appspot.com/ > /dev/null
  echo 'ping list scala end.'
  a=`expr $a - 1`
  echo $a
  sleep 360
done

