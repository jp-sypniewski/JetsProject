## Jets Project

### Week 4 Homework - Skill Distillery

#### Overview

The Jets project uses the JetsApplication to take in txt files of jets and pilots (as csv), then presents the user with functionality to:
- list all jets in the airfield
- fly all the jets, or one jet as selected by the user
- view the fastest jet or the jet with the longest range
- load all cargo jets, 'dogfight' all fighter jets
- add or remove a jet in the airfield

Once initialized from the txt files, the project iterates over the pilots set to assign a pilot to a jet, with pilot as a field in a Jet object.  If a jet has a pilot, the pilot's information is listed with the jet's information and is displayed when the jet flies.

#### Concepts

- Creating objects from a .txt file through FileReader and BufferedReader
- Creating different objects that descend from an abstract superclass
- Iterating over a HashSet, assigning the objects within a set to items within an ArrayList
- Utilizing interfaces that differ by subclass of Jet, with 'instanceof' check to confirm before casting the object and calling methods
- Adding/removing objects from an ArrayList

#### Technologies Used

- Java

#### Lessons Learned

- Readability of code - variable names should be descriptive.  For anyone reading the code, helpful variable names make allow for easier comprehension of how data is manipulated.
- Compare object-type using 'instanceof' - allows for a confirmation of 'is-a' class-type in a much more readable format than e.g. ArrayList.Object.getClass.getSimpleName.equals("ClassName")
