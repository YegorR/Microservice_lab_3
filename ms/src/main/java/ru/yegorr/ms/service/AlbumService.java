package ru.yegorr.ms.service;

import ru.yegorr.ms.dto.request.*;
import ru.yegorr.ms.dto.response.*;
import ru.yegorr.ms.exception.*;

import java.util.*;

public interface AlbumService {

    FullAlbumDto createAlbum(CreateAlbumDto createAlbumRequest, Long musicianId)
            throws ClientException;

    FullAlbumDto changeAlbum(ChangeAlbumDto changeAlbumRequest, Long albumId)
            throws ClientException;

    FullAlbumDto getAlbum(Long albumId) throws ClientException;

    void deleteAlbum(Long albumId) throws ClientException;

    List<BriefAlbumDescriptionDto> searchAlbums(String query);
}
