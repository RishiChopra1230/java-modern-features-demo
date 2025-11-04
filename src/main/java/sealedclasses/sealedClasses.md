
# Inspiration
- Java has concept of abstract base class and final class. Both are two extremes
  - abstract base classes can have any number of subclasses
  - final classes can have none subclasses
- Sometimes we need something in between; we need to be able to restrict the specific subclasses a class may have.
  - The need to restrict subclasses may arise if you create libraries.
  - if your application facilitates a plugin architecture and other developers provide modules to integrate at runtime.

# Introduction 
- Starting with Java 15, we can mark an interface or a class with the sealed keyword. 
- A sealed interface or class provides, implicitly or explicitly, a permits list of derived interfaces or classes. 
  - Any interface or class that isn’t in the list is disallowed from inheriting the sealed interface or class.
- Only those who have the ability to access and modify the source code for the interface or class will be able to modify the hierarchy at anytime in the future.
- The permits list defined by the authors of the interface or class is stored as metadata in the bytecode.

# Deepdive
- By marking the class as sealed, we’re telling the compiler to recognize the permitted list of classes that can inherit from this class. 
- If we don’t provide the permits list, then all the subclasses of a sealed class are required to be in the same file.
- A sealed class/interface is required to have at least one derived class. Otherwise, the  compiler will give an error—you might as well define a class final if you don’t
  plan to have any derived classes.
- Derived classes from a sealed base class needs to be marked as either final or non-sealed to inform compiler of our intention
- We can mark regular classes as sealed as well, but we’re not allowed to mix final and sealed for obvious reasons. We can also mark an interface as sealed
- Both classes and interfaces may or may not be marked with the sealed keyword.
- If marked as sealed, then a permits list is necessary, whether defined implicitly or explicitly. 
- The Class metadata of the JDK has been changed to add functions to provide these details at runtime.
  - isSealed() method of Class to find if a class or an interface is sealed or not
  - getPermittedSubClasses() to get the permitted list if the class or interface is sealed. If it’s not sealed, however, sadly, the getPermittedSubClasses() method
    returns a null
- javap -v <sealedclass> will detail the permitted subclasses as well

# permits 
- Use the permits clause to define a permitted subclasses list, or simply the permits list, for a sealed class or sealed interface. Using the clause is all or
nothing. You can either leave out the permits clause to implicitly define the permits list, or you may use the permits clause to explicitly list all the permitted
subclasses, even if some or all of them are in the same file. Use one or the other approach—don’t list some and expect the compiler to pick up the rest
from the current file.
- The permits clause followed by the list of classes, if present, should be right before the { that starts the body of the class. If the class extends other
  classes or implements interfaces, place those details before the permits clause.

# Subclasses Constraints
- Java places two main constraints on the permitted subclasses (Both of these constraints are verified and enforced by the compiler)
  - It constrains the package to which the subclasses may belong to.
    - The subclasses of a sealed class or a sealed interface should be in the same package as the base class if the base class belongs to
      an unnamed module. (classes by default belong to the unnamed module if we don’t use the Java modules.)
    - When not using Java modules, a sealed class or interface and all its permitted subclasses are required to be in the same package, though they may be placed
      in different files.
    - If Java modules are used, then the subclasses may be in any package as long as the base and the subclasses are all part of the same module.
  - It constrains the declaration that the subclasses should carry.
    - The subclasses that implement a sealed interface or extend from a sealed class are required to be marked with exactly one of the following:
      final, sealed, or non-sealed.
    - final closes the inheritance hierarchy nicely and cements that no further extension beyond that class in that branch of inheritance hierarchy is
      possible. It’s the common, more stringent, and arguably the most sensible wrong with it. You can always change it to something less stringent later on
      option. Choose this if you’re not sure which one to choose—it’s hard to go if necessary.
    - sealed reopens the hierarchy for at least one more level of extension
    - non-sealed opens up the hierarchy for unrestricted extension, starting from the declaring class or interface, and is the option that might be used the least. non-sealed counteracts sealed; the
      sealed declaration closes the hierarchy to the permitted subclasses whereas non-sealed allows any subclass to freely extend the hierarchy.
      - Use the non-sealed option to say “the hierarchy is closed everywhere, except right here.”
      - the compiler doesn’t insist that any class inherit from an interface declared as non-sealed or extend from a class marked as non-sealed.

# Wrapping Up
Sealed classes and interfaces fill a gap in object-oriented modeling in Java.
They provide the ability to restrict the hierarchy of inheritance so third-party
libraries can use your classes, but may not inherit from them. Using the
newly added facilities you can evolve the inheritance hierarchy in a controlled
manner to more closely match the requirements of your domain.