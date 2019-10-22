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

# Creating complete continuous deliver system

