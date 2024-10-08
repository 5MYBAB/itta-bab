package com.fivemybab.ittabab.group.query.dto;

import com.fivemybab.ittabab.group.command.domain.aggregate.ChatRoomStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class GroupInfoDto {

    private Long groupId;
    private Long groupCategoryId;
    private Long userId;
    private String groupTitle;
    private int userCounting;
    private boolean groupStatus;
    private LocalDateTime createDate;
    private LocalDateTime endDate;
    private String groupPost;
    private boolean isBlinded;
    private ChatRoomStatus chatRoomStatus;
}
