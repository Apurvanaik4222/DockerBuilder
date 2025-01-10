#!/bin/bash

#-------------------------------------------------------------------
#  This script expects the following environment variables:
#     HUB_HOST
#     BROWSER
#     THREAD_COUNT
#     TEST_SUITE
#-------------------------------------------------------------------

# Print the received environment variables
echo "-------------------------------------------"
echo "HUB_HOST      : ${HUB_HOST:-hub}"
echo "BROWSER       : ${BROWSER:-chrome}"
echo "THREAD_COUNT  : ${THREAD_COUNT:-1}"
echo "TEST_SUITE    : ${TEST_SUITE}"
echo "-------------------------------------------"

# Check if the Selenium Hub is reachable
if ! curl -s http://${HUB_HOST:-hub}:4444/status; then
    echo "Error: Unable to reach Selenium Hub at http://${HUB_HOST:-hub}:4444"
    exit 1
fi

# Wait for the hub to be ready
echo "Checking if hub is ready..."
count=0
while [ "$(curl -s http://${HUB_HOST:-hub}:4444/status | jq -r .value.ready)" != "true" ]; do
    count=$((count + 1))
    echo "Attempt: ${count}"
    if [ "$count" -ge 30 ]; then
        echo "**** HUB IS NOT READY WITHIN 30 SECONDS ****"
        exit 1
    fi
    sleep 1
done

echo "Hub is ready after ${count} attempts!"
echo "Selenium Grid is up and running. Running the tests..."

# Start the tests
java -cp 'libs/*' \
     -Dselenium.grid.enabled=true \
     -Dselenium.grid.hubhost="${HUB_HOST:-hub}" \
     -Dbrowser="${BROWSER:-chrome}" \
     -DCONFIG_PATH="${CONFIG_PATH:-/home/selenium-docker/config/global.properties}" \
     org.testng.TestNG \
     -threadcount "${THREAD_COUNT:-1}" \
     testSuites/"${TEST_SUITE}"
