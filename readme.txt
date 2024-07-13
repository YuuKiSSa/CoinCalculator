1. Download the files
2. cd to backend file in cmd, run mvn clean package
3. run docker build -t backend-app .
4. run docker container : docker run -p 8080:8080 backend-app
5. If you want to run application on local machine, change (/frontend/src/App.js in line 14) host to localhost
6. If you want to deploy the application on server, change host to your public IPv4 address, and upload to docker hub
7. open another cmd window, cd to frontend file run docker build -t frontend-app .
8. run docker container: docker run -p 80:80 frontend-app
9. now you can access the application use http://localhost:80
