package records.interfaceimpl;

public record RecordWithInterface(String firstName, String lastName) implements Json{
    @Override
    public String generateJson() {
        return """
                {
                "firstName": "%s",
                "lastName": "%s"
                }
                """.formatted(firstName, lastName);
    }

    public static void main(String[] args) {
        var recordWithInterface = new RecordWithInterface("Rishi", "Chopra");
        System.out.println(recordWithInterface.generateJson());
    }
}
