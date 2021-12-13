package ru.yegorr.ms.service;

import ru.yegorr.ms.dto.response.*;
import ru.yegorr.ms.exception.*;

import java.util.*;

public interface TrackService {

    TrackDto getTrackInfo(Long trackId) throws ClientException;

    List<TrackWithPlaysNumberDto> searchTracks(String query) throws ClientException;
}
