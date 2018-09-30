#!/usr/bin/env bash

export CATALINA_HOME=/etc/init.d/tomcat8
export TOMCAT_HOME=/usr/share/tomcat8

stop_tomcat()
{
  sudo $CATALINA_HOME stop
}

start_tomcat()
{
  sudo $CATALINA_HOME start
  end
}

rename() {
  rm build/libs/crud.war
  if mv ./build/libs/tasks-0.0.1-SNAPSHOT.war ./build/libs/crud.war; then
     echo "Successfully renamed file"
  else
     echo "Cannot rename file"
     fail
  fi
}

copy_file() {
  if cp build/libs/crud.war $TOMCAT_HOME/webapps; then
     start_tomcat
  else
     fail
  fi
}

fail() {
  echo "There were errors"
}

end() {
  echo "Work is finished"
}

if gradle build; then
   rename
   copy_file
else
   stop_tomcat
   fail
fi
