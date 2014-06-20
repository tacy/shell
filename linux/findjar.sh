while [ "`find . -type f -name '*.zip' | wc -l`" -gt 0 ]; do find -type f -name "*.zip" -exec unzip -- '{}' \; -exec rm -- '{}' \;; done


find -name "*3.0.6.RELEASE.jar" -exec sh -c 'unzip -l "{}" | grep -q stereotype.Controller' \; -print
