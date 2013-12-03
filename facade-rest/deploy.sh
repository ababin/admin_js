#!/usr/bin/env bash  

mvn clean install

# stop jetty
/opt/jetty/bin/jetty.sh stop

# clear log folder
rm -R /opt/jetty/logs/**

# copy application
cp /home/alexander/projects/prospring3/ch16/target/abc.war /opt/jetty/webapps/abc.war

# start jetty
/opt/jetty/bin/jetty.sh start

sleep 5s

tail -f /opt/jetty/logs/*.log