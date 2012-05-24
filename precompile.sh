#!/bin/bash
set -e
coffee -c -o resources/public/javascript/ src/gipf_online/web/coffee/
sass --update src/gipf_online/web/scss:resources/public/css
