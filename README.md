## Jets Project

### Week 4 Homework - Skill Distillery

*Note: basic requirements met as of commit "basic requirements met", commits after this include code for stretch goals*

#### Overview

The Jets project uses the JetsApplication to take in a .txt file of Jets (as csv), then presents the user with functionality to:
- list all jets in the airfield
- fly all the jets
- view the fastest jet or the jet with the longest range
- load all cargo jets
- add or remove a jet in the airfield

#### Concepts

- Creating objects from a .txt file through FileReader and BufferedReader
- Creating different objects that descend from an abstract superclass
- Utilizing interfaces that differ by specific type of object, requiring 'instanceof' check to confirm before casting the object and calling methods
- Adding/removing objects from an ArrayList

#### Technologies Used

- Java

#### Lessons Learned

- Readability of code - everything for this project can be kept inside a main, but refactoring into methods provides drastically increased readability.
- 'instanceof' - allows for a confirmation of 'is-a' class-type in a much more readable format than e.g. ArrayList.Object.getClass.getSimpleName.equals("ClassName")


#### Potential Updates

- interface for the application
