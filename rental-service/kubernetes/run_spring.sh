#!/bin/bash

kubectl apply -f .

sleep 5

kubectl port-forward deployment.apps/app 9010
kubectl port-forward deployment.apps/rental 9011
kubectl port-forward deployment.apps/comment 9012
