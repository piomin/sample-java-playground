package pl.piomin.services.playground.model.record;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record Employee(String firstName, String lastName, String position, int salary) {
}
