# CI CD
## Continuous Delivery
* common problem: how to release implemented code quickly/safely and sustainably?
* Solution: Continuous Delivery
** automates manual tasks so users receive new features right after implementation

## Traditional Delivery Process
* See image introducing traditional delivery process
* Three phases after requirements: development, quality assurance (tests), operations
** shortcomings: slow delivery (outdated requirements), long feedback cycle (users, developers),
lack of automation (unpredictable releases), risky hotfixes (barely tested), stress (ops, teams), 
poor communication (over the fence --> blaming game), shared responsibility (no team takes it), 
lower job satisfaction

## Benefits of CD
* Goal: change each phase of the traditional delivery process into a sequence of scripts (automated deployment pipeline/CD pipeline)
** Benefits: fast delivery, fast feedback cycle, low-risk releases, flexible release options

# The automated deployment pipeline
* Goal: automate as much as possible of the 3 phases (dev, UAT, Ops)
* Code Change 
--> Continuous Integration (code integrates together) 
--> Automated Acceptance Testing (implemented features meet client's requirements)
--> Configuration Management (configures & deploys software)
* CD Pipeline = sequence of scripts, execute after every committed code change. Success = deploy to production

## Continuous Integration 
* anything fails: pipeline execution stops --> devs fix it
* needs to be fast (not hours!)
* simple to define (is part of development, no agreement with QA/Ops needed)

## Automated Acceptance Testing
* Suite of tests written together with Clients and QAs
* Replaces manual UAT
* Quality gate (is product ready for release?)
* Fail --> Pipeline stops, no release
* Goal: build quality into product, not verify it later
* most difficult to automate: requires client cooperation and creating tests in the beginning (not end)
* What to test and where? Agile testing matrix:
** Acceptance Testing (automated): functional requirements from business view. Stories/Examples written by clients/devs to agree on how the SW should work
** Unit Testing (automated): help devs provide high-quality SW, minimise # bugs
** Exploratory (manual): manual blackbox testing (to break or improve the system)
** Non-functional testing (automated): test performance, scalability, security, etc. 
* Role of a QA in CD: 
** Manual QA:  Exploratory testing
** AUtomation QAs: help with non-functional/acceptance testing (e.g. write code to support load testing)
** no special place, just part of the development team
* Where are integration tests? Somewhere between acceptance/unit tests, more technical than acceptance. 
Implemented as a separate phase in the continuous delivery pipeline
* The testing pyramid
** Higher up: slower
** Acceptance test should *not* show 100% coverage (should be feature-oriented, verify selected scenarios)
** Unit tests: cheap and fast --> strive for 100% coverage

## Configuration Management
* Tracking/controlling changes in SW and its environment
* Prepares/installs tools, scales # of service instances/distribution, infrastructure inventory, deployment tasks 

# Continuous Delivery Prerequisites
## Organisational Prerequisites
* DevOps culture: Team is responsible for dev, QA, and Ops
** Productivity is not lost due to automation of QA/Ops steps
** Starts usually with a team that has 4 devs, 1 op, 1 QA, sitting close together
## Client/Product Owner in the Process
* traditionally: define requirements, answer questions, attend demos, take part in UAT
* CD: no UAT, client writes acceptance tests (need to be more technical)
** Requirements/User Stories can/should have some acceptance tests attached
## Business Decisions
* Business usually sets release schedule
* Feature Toggles/manual pipeline steps may help
* Continuous Deployment: each commit passing tests is deployed
* Continuous Delivery: each commit is a release candidate (release may be manual step)
## Technical & Development Prerequisites
Requirements:
* automated build, test, package, deploy operations: all need to be automatable
* Quick pipeline execution: 5 - 15 mins
* QUick failure recovery
* Zero-downtime deployment: release many times a day, not possible to be down
* Trunk-based development: devs check into master branch regularly (else integration / releases are rare)
* More on process: Continuous Delivery: Reliable Software Releases through Build, Test, and Deployment Automation (Jez Humble/David Farley)
# Building CD Process
## Tools
* Any tool can be replaced by another tool
* Docker: containerisation (package app in env-agnostic image, no server configuration needed)

Docker Hub (registry for docker images)
Docker Compose (tool to define multicontainer Docker apps),
Docker swarm (clustering / scheduling tool)

* Jenkins: automation server for creation of CI/CD pipelines
* Ansible: software provisioning, configuration management, application deployment, docker integration
* Github, Java/Spring Boot/Gradle
* Acceptence Tests: Cucumber/Fitness/JBehave
* DB: Flyway, Liquibase

## Questions
- 3 Phases of traditional Delivery Process: Development, QA, Operations
- 2 Main Stages of CD Pipeline: CI, AAT, Config Management
- 3 Benefits of CD: fast delivery, short feedback cycle, low-risk releases, release options
- Automatable Tests: Unit, Acceptance, Non-Functional
- More Integration or Unit Tests? Unit (faster, cheap)
- DevOps? Dev-QA-Ops, everybody does everything
- Software Tools in the Book: Docker (Host, Registry, Image), Jenkins, Gradle, Github, Java, Spring Boot, Kubernetes, Cucumber, Ansible, Flyway

# Creating complete continuous deliver system
* introducing docker - Docker Host \[Containerized Application\] (access app as if it ran directly on the host machine (through port forwarding/publishing (Docker)))
* configuring Jenkins - Jenkins Master accepts build request, execution is started on one of the Jenkins Slave (agent) machines
* CI Pipeline  - commit stage: every commit to github triggers the jenkins build --> compile/unit tests/code coverage, static analysis, etc --> notification to devs
* Automated acceptance testing - merges docker & Jenkins w/ automated acceptance testing (aat) in Cucumber (docker registry (repo for docker image), docker host (pulls app from registry and starts it for aat))
* Clustering w/ Kuberentes - 1 Docker Host replaced with Kubernetes Cluster, 1 standalone app replaced with two dependent containerized apps
* Configuration Management w/ Ansible - multiple environments w/ Ansible (Testing/ Staging / Prod), deployment of the same app (kubernetes cluster) on multiple machines
* CD Pipeline/advanced CD - deploy app to prod env w/ many instances & automatic db schema management w/ Flyway migrations

# Docker
## Questions
* main difference between containerization (docker) and virtualization (virtualbox)?
** No Guest OS in containers. Dependencies still managed per container, not shared through Host OS
* benefits of providing app as Docker image (min. 2)?
** higher performance, smaller resource consumption, smaller images
** Environment, Isolation, Application Organisation, Portability,
* Can Docker Deamon be run natively on Windows/macOs?
** No, uses Virtual Machine
* Difference between Docker image and Container?
** like class and object: image = describes app, container = an instance of image
* What does it mean that docker images have layers?
* 2 Methods to create docker image?
* command to create docker image from Dockerfile?
* command to run docker container from docker image?
* what is "publishing a port"?
* what is a docker volume?

## What is Docker?
Docker containers wrap a piece of SW in a complete filesystem that contains everything to run: code, 
runtime, system tools, system libraries  anything can be installed on a server. This guarantees that the software
will always run the same regardless of its environment
* packaging an app into an image and run it anywhere (similar to virtualisation)

## Containerization vs. Virtualization
* Virtualisation: Virtual Machines (VirtualBox etc.): like physical machine ( see Pic ), with OS
** Drawbacks: low performance, high resource consumption, large image size
* Containerisation: NO OS, Apps directly interface with Host OS, no Guest OS: better performance, no waste of resources, smaller images
** Each container has own libraries in right version, no interdependencies between containers

## Docker for Ubuntu
page 39
## Docker images & containers
image = stateless collection of all files necessary to run an app with the recipe of how to run it
container = stateful instance of an image. N containers per 1 Image possible, state changes possible to container
Base image = usually OS, build images on top of it (e.g. ubuntu base image, add git image, add jdk image, etc.)
--> container on top is able to download java project from github and compile & run a jar file
Docker Hub: contains lots of images, official image usually the one without prefixes

#Building images
- real power of docker: building own docker images that wrap the program together with its environment
* Built in language to specify instructions to be executed to build docker image: Dockerfile

## Commands
### Committing image from container from commandline
* *docker run -i (interactive) -t ubuntu:18.04 /bin/bash* # runs ubuntu cmd --> can apt-get install things like java
* docker commit {docker-container-hash} {name-of-new-docker-image} # creates a new docker image based on a configured container
* docker container ls -a # list all docker containers (running and old)

### Committing image from Dockerfile from cmd
* Windows cmd new file: type nul > file-name.file-ending
* Windows cmd edit file: notepad filename
* Add FROM ubuntu:18.04 RUN apt-get update && apt-get install -y git && apt-get install -y openjdk-11-jdk
** FROM: on top of which image to build an image from
** RUN: specifies commands to run _inside_ the container
** COPY: copies files/directory into filesystem of an image (from host to container, needs (.) at the end?)
** ENTRYPOINT: defines which app should be run in the executable container
* docker build -t {new_image_name} . # inside the Dockerfile containing directory, dot (.) needed at the end!

## Environment Variables:
* Either inside Dockerfile "ENV {Env-Name} {Value}" (ENV NAME Oliver) or
* As parameter in docker run -e NAME=Oliver hello_world_java
--> Env in Parameter overrides Env in Dockerfile!

## Docker container states
Apps that should run continuously: run in background: add -d (--detach) option to run
* docker run -d -t ubuntu:18.04
* print running containers: docker ps
* print all containers: docker ps a
* Container states: exited, paused, restarting, and running
** Don't pause... it freezes the processes with SIGSTOP signal
* docker stop {container-hash}

# Docker Networking
* run a service and expose its ports to other apps

## Running Services 
* docker run -d tomcat (access at port 8080, but its inside the container)
* logs from -d services: docker logs {container hash}
* access through publishing (port mapping): docker run -p (--publish) <host_port (external)>:<container_port (internal)>

## Container Networks
