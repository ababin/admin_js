#!/usr/bin/env bash  

mvn clean install

# stop jetty
/opt/jetty_js/bin/jetty.sh stop

# clear log folder
rm -R /opt/jetty_js/logs/**

# copy application
cp /home/alexander/projects/admin_js/facade-rest/target/abc.war /opt/jetty/webapps/abc.war

# start jetty
/opt/jetty/bin/jetty.sh start

sleep 5s

tail -f /opt/jetty/logs/*.log