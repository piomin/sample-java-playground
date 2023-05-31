package pl.piomin.services.playground.model.record;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record Person(String firstName, String lastName, int age) {

}
