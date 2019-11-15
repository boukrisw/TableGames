#!/bin/sh

parcours () {
	rm *.class
	for file in *
	do
		if [ -d "$file" ]
		then
			echo "Entrée dans le répertoire $file"
			cd "$file"
			parcours
			echo "Sortie du répertoire"
			cd ..
		fi
	done
}

parcours
