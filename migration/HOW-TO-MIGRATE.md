# How-To Migrate

* Open a shell tool.
* Change to folder with `cd ~/Eclipse-Workspace/carp-vtt`.
* Change to sub-project "legacy-adapter".
* Type `java -Dspring.datasource.password=password -jar target/carp-vtt-legacy-adapter-0.0.1.jar`.
* Open a new tab in the shell tool.
* Change to sub-project "migration".
* Type `./start.sh password`.