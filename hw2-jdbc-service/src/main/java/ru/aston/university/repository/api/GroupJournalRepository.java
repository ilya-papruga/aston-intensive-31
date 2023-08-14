package ru.aston.university.repository.api;

import ru.aston.university.dto.GroupDto;
import ru.aston.university.dto.GroupJournalDto;

import java.util.List;

public interface GroupJournalRepository extends AutoCloseable {

    /**
     * Получить все журналы с групп и студентов
     */
    List<GroupJournalDto> readAll();


    /**
     * Получить журнал студентов группы
     *
     * @param groupNumber номер группы
     */
    GroupDto readJournal(String groupNumber);

}
