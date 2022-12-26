package ru.stqa.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
      ContactDataGenerator generator = new ContactDataGenerator();
      JCommander jCommander = new JCommander(generator);
      try {
        jCommander.parse(args);
      } catch (ParameterException e) {
        jCommander.usage();
        return;
      }

      generator.run();
    }

    private void run() throws IOException {
      List<ContactData> contacts = generateContacts(count);

      if (format.equals("json")) {
        saveAsJson(contacts, new File(file));
      } else {
        System.out.println("Unrecognized format " + format);
      }
    }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try(Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

    private List<ContactData> generateContacts(int count) {
      List<ContactData> contacts = new ArrayList<>();
      for (int i = 0; i < count; i++) {
        contacts.add(new ContactData().withLastname(String.format("Last name %s", i))
                .withFirstname(String.format("First name %s", i)).withMiddlename(String.format("Middle name %s", i))
                .withAddress(String.format("Address %s", i)).withHomePhone(String.format("Home phone %s", i))
                .withMobilePhone(String.format("Mobile phone %s", i)).withWorkPhone(String.format("Work phone %s", i))
                .withEmail(String.format("%s@%s.ru", i, i)).withEmail2(String.format("%s@%s.2ru", i, i))
                .withEmail3(String.format("%s@%s.3ru", i, i)).withHomePhone2(String.format("Secondary phone %s", i)));
      }
      return contacts;
    }
  }
