#!/bin/bash
set -e
# run precompile apps in the background (though output is in foreground so you can see errors!)
#  you could do this manually in separate terminals if you want more control
#  or kick this off from rake...
sass --watch src/gipf_online/web/scss:resources/public/css &
coffee -o resources/public/javascript --watch src/gipf_online/web/coffee/ &

