package ru.aston.university.service.api;

import ru.aston.university.dto.GroupDto;
import ru.aston.university.dto.GroupJournalDto;

import java.util.List;

public interface GroupJournalService {

    GroupDto readJournal(String groupNumber);
    List<GroupJournalDto> readAll();

}
