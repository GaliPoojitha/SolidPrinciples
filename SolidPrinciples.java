/**
 * Let’s kick things off with the SOLID principles — trust me, once you understand these,
 * you’ll start seeing how most design patterns naturally align with them.
 *
 // Now you might be wondering: “Wait, didn’t we already learn OOP? What’s all this again?
 * Good question. Think of it this way:
 *
 * OOP gives us the *concepts* — the foundation of Object-Oriented Programming.
 * SOLID, on the other hand, offers *principles* — best practices, rules, or wise advice
 * that help you apply those concepts effectively in real-world software.
 *
 * These principles make your code more:
 *      Maintainable ✅
 *      Scalable ✅
 *      Reusable ✅
 * We’ll call this trio "MSR" from now on — because we’ll be referring to them a LOT.
 */

/** 
 * Starting with "S" — Single Responsibility Principle (SRP)
 * This principle says a class should have only *one reason to change* — it should handle a single responsibility.
 * This makes scaling, changing, or testing your code way easier — no side effects!
 */

// Example story:
// Imagine you're running a school. You create one Teacher class responsible for everything:
// teaching, attendance, and marks. Sounds simple but problematic!

/*class Teacher {
    public void teachAllSubjects() {
        System.out.println("Teaching all subjects... ");
    }

    public void markAttendance() {
        System.out.println("Marking attendance for every student in every subject... ");
    }

    public void assignMarks() {
        System.out.println("Assigning marks for all subjects... ");
    }
}*/

// Problem? Changing something in teaching may accidentally break attendance or marks logic.
// That’s violating SRP — one class doing *too much* makes maintenance and scaling hard.

/** 
 * Refactoring with SRP:
 * Break responsibilities into separate classes — one for teaching, one for attendance, one for marks.
 */

/*class SubjectTeacher {
    private String subject;

    public SubjectTeacher(String subject) {
        this.subject = subject;
    }

    public void teach() {
        System.out.println("Teaching " + subject ");
    }
}

class AttendanceTracker {
    public void markAttendance(String subject) {
        System.out.println("Marking attendance for " + subject ");
    }
}

class MarksManager {
    public void assignMarks(String subject) {
        System.out.println("Assigning marks for " + subject ");
    }
}

// Now each class has a *single* responsibility and does it well — this is SRP in action!

// Example usage:
/*public class Main {
    public static void main(String[] args) {
        String subject = "Mathematics";

        SubjectTeacher mathTeacher = new SubjectTeacher(subject);
        AttendanceTracker attendance = new AttendanceTracker();
        MarksManager marks = new MarksManager();

        mathTeacher.teach();
        attendance.markAttendance(subject);
        marks.assignMarks(subject);
    }
}*/

/**
 * Magic of SRP:
 * - Add new topics? Just change SubjectTeacher.
 * - Change attendance rules? Modify AttendanceTracker only.
 * - Update grading logic? Tweak MarksManager alone.
 *
 * Also notice **encapsulation** here — each responsibility is wrapped in its own class.
 */

/*
 * Next up: "O" — Open/Closed Principle (OCP)
 * Classes should be **open for extension** but **closed for modification**.
 * This means you can add new features without changing existing code.
 */

// Bad example: Adding new duties via if-else in the same method leads to messy, error-prone code.

/*class SubjectTeacher {
    private String subject;

    public SubjectTeacher(String subject) {
        this.subject = subject;
    }

    public void performDuty(String type) {
        if (type.equals("teaching")) {
            System.out.println("Teaching students");
        } else if (type.equals("correcting")) {
            System.out.println("Correcting exam papers");
        }
    }
}*/

/*
 * Better: Use interfaces and polymorphism to extend behavior without modifying existing code.
 */

/*interface Responsibility {
    void perform();
}

class Teaching implements Responsibility {
    public void perform() {
        System.out.println("Teaching students");
    }
}

class Correcting implements Responsibility {
    public void perform() {
        System.out.println("Correcting exam papers");
    }
}

class Teacher {
    // OCP: extend duties by passing new Responsibility implementations
    public void performDuty(Responsibility responsibility) {
        responsibility.perform();
    }
}*/

/*Teacher teacher = new Teacher();
teacher.performDuty(new Teaching());
teacher.performDuty(new Correcting());*/

/**
 * See? No existing code was changed, just extended with new classes.
 */

/*
 * "L" — Liskov Substitution Principle (LSP)
 * Subtypes must be substitutable for their base types without breaking correctness.
 */

// Problem example:
/*class Bird {
    void fly() {
        System.out.println("Flying");
    }
}

class Ostrich extends Bird {
    @Override
    void fly() {
        throw new UnsupportedOperationException("Ostrich can't fly!");
    }
}*/

// Fix: separate interfaces for different behaviors:

/*interface Bird {
    void makeSound();
}

interface FlyingBird extends Bird {
    void fly();
}

class Sparrow implements FlyingBird {
    public void fly() {
        System.out.println("Sparrow flying");
    }

    public void makeSound() {
        System.out.println("Tweet tweet");
    }
}

class Ostrich implements Bird {
    public void makeSound() {
        System.out.println("Boom boom");
    }
}*/

/*
 * This design respects LSP — no unexpected behavior or broken expectations.
 */

/*
 * "I" — Interface Segregation Principle (ISP)
 * Clients should not be forced to depend on methods they do not use.
 * Design small, specific interfaces rather than one huge "god" interface.
 */

// You’ve already applied this by splitting Responsibility interfaces, e.g., PhysicalExercise vs Responsibility.

/*
 * "D" — Dependency Inversion Principle (DIP)
 * High-level modules should not depend on low-level modules; both should depend on abstractions.
 * Abstractions should not depend on details; details should depend on abstractions.
 */

// Real-life analogy:
// Organizing a school event — define roles (Host, Performer, Usher) rather than hardcoding who does what.
// If someone falls sick, just replace the person in the role — plan remains intact.

/*
 * Now, here’s the final example with all SOLID principles applied:
 */

public class SolidPrinciples {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();

        Responsibility teaching = new Teaching();
        Responsibility correcting = new Correcting();
        PhysicalExercise yoga = new Yoga();

        System.out.println("Performing teaching duty:");
        teacher.performDuty(teaching);

        System.out.println("\nPerforming correcting duty:");
        teacher.performDuty(correcting);

        System.out.println("\nPerforming yoga duty (via PhysicalExercise interface):");
        // Note: Yoga is a PhysicalExercise, which extends Responsibility,
        // so it can also be passed to performDuty since perform() is defined.
        teacher.performDuty(yoga);
    }
}

// Interfaces and classes (for completeness)

interface Responsibility {
    void perform();  // ISP: small, focused interface
}

class Teaching implements Responsibility { // SINGLE RESPONSIBILITY PRINCIPLE
    public void perform() {
        System.out.println("Teaching students");
    }
}

class Correcting implements Responsibility {
    public void perform() {
        System.out.println("Correcting exam papers");
    }
}

// ISP: Segregate physical exercise responsibilities
interface PhysicalExercise extends Responsibility {
    void exercise();
}

//violating LSP
/*class Yoga implements Responsibility {
    public void perform() {
		
        throw new UnsupportedOperationException("Yoga isn't supported this way");
    }
}*/
class Yoga implements PhysicalExercise { // now Yoga only implements what it supports--> ISP not violating LSP
    public void perform() {
        exercise();
    }
    public void exercise() {
        System.out.println("Performing yoga");
    }
}

class Teacher {
    // DIP: Teacher depends on abstraction (Responsibility), not concrete classes
    public void performDuty(Responsibility responsibility) {
        responsibility.perform();
    }
}

/*
 * Explanation of principles in code:
 * - SRP: Each class (Teaching, Correcting, Yoga) has a single responsibility.
 * - OCP: Teacher can accept any new Responsibility without modifying Teacher class.
 * - LSP: Responsibilities properly implement Responsibility interface, so they can be substituted.
 * - ISP: Responsibility interface is focused and not bloated.
 * - DIP: Teacher depends on the abstraction Responsibility, not on concrete implementations.
 */

/*
 * And that’s SOLID — your toolkit for writing clean, maintainable, scalable, and reusable code!
 * Keep practicing these, and your code will become a joy to build and maintain.
 */
 
 
 // ******************* PLEASE CREATE YOUR OWN PROJECTS ONCE YOU READ A CONCEPT*************************************************
