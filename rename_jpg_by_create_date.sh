for i in $(ls *.JPG); do exiv2 -r '%Y%m%d.%H%M%S.:basename:' rename $i; done
