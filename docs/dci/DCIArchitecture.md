# DCI Architecture
#### Sequence of tasks (use cases) to achieve an end user goal
A sequence of tasks achieves some goal for an end user in a context.
These tasks are captured by use cases.

DCI minimises any gap existing between the programmer's mental model 
and the program actually executing in the computer.

## DCI in a Nutshell

![dci_overview](images/dci.PNG "DCI overview")

Encapsule the "what the system does code" on a "per-scenario" basis.

DCI separates the architecture into _data_ part (what the system _is_) and
_interaction_ part (what the system _does_).

The _interaction_ (I) part becomes connected to the _data_ (D) 
part on an event-by-event basis by an object called _Context_ (C) --> *DCI*

Data objects == Dumb domain entities, end user mental model. No operations, only domain properties.

Interaction expresses Use Case.

Each environment (e.g. MVC Controller object) finds a context, depending on an event that occurs,
that understands the objectrole-to-object mapping for the use case triggered by that event.

Each domain object can play an object _role_.

The controller wires up the object roles to the objects, and then starts the execution by 
invoking the _object role method_ that initiates the use cases (trigger).

Each object can play several object roles, and a given object role may be played by a 
combination of several objects together.

## Overview of DCI
Goal of DCI: separate system state from system behaviour code

### Forgotten parts of the user mental model
What the system is: classes and objects
What the system does: methodless object roles and methodful object roles
[!ocmm](images/dci_co_or.PNG "classes objects roles")

#### Methodless Object Roles
Roles == Identifiers == Methodless Object Role Type == Interface
Roles: Identifiers in the Programming Language.

Identifiers may be pointers to objects to which the role is bound, function invokations, etc.

Identifiers can be typed: types are _methodless object role types_.

Identifiers are interface to the role: collected responsibilities (Java interfaces e.g.)
Method are not filled with code.

Object roles and types are the functional architecture of the system:
document/codify the contracts of how system parts interact to accomplish an end user's goal.
They are _form_, not _structure_.

#### Methodful Object Roles
Java Interfaces/abstract base classes

Methods are filled with code.

What the system _does_.

Use Cases and Habits.

They are _structure_, not _form_.

Goal: separate use case scenario (behaviour) from domain objects. 

Methodless role types often act as base type for the methodful roles.

Objects roles capture what domain objects do. 
Need to combine domain objects (what the system is) with scenario (what the system does).
This can be done by injecting the scenario code into the classes from which those objects were created.
This can be done with Traits.

#### Tricks with traits
Trait = Holder of stateless methods (mix-ins), Java: Default methods

Trait acts as object role, domain class has it injected/implements it

For methodful object roles

Methods of the object role can invoke methods of the domain class into which they are injected

Each object can take on all object roles for all scenarios it is designed to support

### Context classes: One per Use Case (9.3.4)
1 context class for each use case and for each habit (not only per use case _scenario_)

A context knows the object roles that are involved in any given use case.

A context's implementation holds the methodless object role identifiers declared in terms of their methodless object role types (interfaces/abstract classes)

For any use case scenario invocation, object roles must be bound to objects



