package example;

public class Main {

  public static void main(String[] args) {

    final String connectionStr = "jdbc:postgresql://db/animal?user=maria&password=pass";

    example.chain.ILink obj = new example.chain.Driver();

    if (obj.hasResource("org.postgresql.Driver", connectionStr)){
      example.db.print.output.IOutput output = new example.db.print.output.ToConsole();
      for ( String tbl : new String[]{"\"public\".\"dog\"", "\"public\".\"breedLookup\""
      , "\"public\".\"colorLookup\"", "\"public\".\"dog_expanded\"", "\"public\".\"breed_count\""})
        example.db.DBQuery.query(connectionStr, tbl, output);
    }
  }
}
