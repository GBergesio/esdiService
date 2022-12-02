package esdi.Services.mappers;

import esdi.Services.common.GenericaMapper;
import esdi.Services.dtos.CommentDTO;
import esdi.Services.models.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CommentMapper implements GenericaMapper<CommentDTO, Comment> {
}
