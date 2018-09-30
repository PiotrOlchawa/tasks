#!/usr/bin/env bash

export BROWSER=/usr/bin/firefox

run_crud()
{
  if ./runcrud.sh ; then
     echo "Successfully build .. Running Browser"
    $BROWSER "http://localhost:8080/crud/v1/task/getTasks"
  else
    fail
  fi
}

fail() {
  echo "There were errors"
}

run_crud
