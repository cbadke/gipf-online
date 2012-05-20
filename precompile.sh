#!/bin/bash
set -e
coffee -c -o resources/public/javascript/ src/web/coffee/
sass --update src/web/scss:resources/public/css
