# Anleitung zum Betrieb des Prototypen

Voraussetzungen:
- Kubernetes v4.5.7, namespace: default
- Docker v20.10.17
- unixoides Betriebssystem oder Linux Bash Shell für Windows
- vegeta v12.8.4

Vorgehensweise:
1. Starten von Docker und Kubernetes
2. Wechsel in das Verzeichnis: rental-service/kubernetes
3. Ausführen der Datei: run_spring.sh
4. POST Request mit Initialwerten 
5. Verbindung zu jconsole in separaten Terminals mit: 
   1. jconsole 127.0.0.1:9010
   2. jconsole 127.0.0.1:9011
   3. jconsole 127.0.0.1:9012
6. Messungen mit vegeta vornehmen
7. Portforwarding in der Konsole mit Strg+C Beenden
8. App beenden mit dem Ausführen der Datei: stop_spring.sh

Repo-Link:
https://github.com/sengsei/microservices_spring