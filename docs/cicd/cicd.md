# CI CD
## Continuous Delivery
* common problem: how to release implemented code quickly/safely
* Continuous Delivery: get changes into production safely/quickly in a sustainable way
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

## The automated deployment pipeline
* Goal: automate as much as possible of the 3 phases (dev, UAT, Ops)
* Code Change --> Continuous Integration --> Automated Acceptance Testing --> Configuration Management
