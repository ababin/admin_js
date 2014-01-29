#!/usr/bin/env bash  

mvn clean install -Dmaven.test.skip=true

# stop jetty
/opt/jetty_js/bin/jetty.sh stop

# clear log folder
rm -R /opt/jetty_js/logs/**
rm -R /opt/jetty_js/work/**

# copy application
cp /home/alexander/projects/admin_js/server_side/facade-rest/target/facade-rest.war /opt/jetty_js/webapps/facade-rest.war

# start jetty
/opt/jetty_js/bin/jetty.sh start

sleep 5s

tail -f /opt/jetty_js/logs/*.log