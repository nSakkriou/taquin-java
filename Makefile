all:
	javac -Xlint:none -d build -cp lib/* @sources.txt
	java -cp build;lib/* taquin.Main

javadoc:
	javadoc -encoding "utf8" -docencoding "utf8" -d doc src/taquin/*.java src/taquin/model/*.java src/taquin/controller/*.java src/taquin/vue/*.java

jar:
	javac -Xlint:none -d build -cp lib/* @sources.txt
	jar cfe dist/taquin.jar build/taquin.Main build/taquin/*
