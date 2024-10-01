package com.fivemybab.ittabab.group.command.application.service;

import com.fivemybab.ittabab.group.command.domain.aggregate.GroupInfo;
import com.fivemybab.ittabab.group.command.domain.aggregate.GroupUser;
import com.fivemybab.ittabab.group.command.domain.repository.GroupCommentRepository;
import com.fivemybab.ittabab.group.command.domain.repository.GroupInfoRepository;
import com.fivemybab.ittabab.group.command.domain.repository.GroupUserRepository;
import com.fivemybab.ittabab.group.query.service.GroupInfoQueryService;
import com.fivemybab.ittabab.group.query.dto.GroupInfoDto;
import com.fivemybab.ittabab.group.query.dto.GroupUserDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupInfoCommandService {

    private final GroupInfoRepository groupInfoRepository;
    private final GroupUserRepository groupUserRepository;
    private final GroupCommentRepository groupCommentRepository;
    private final ModelMapper modelMapper;
    private final GroupInfoQueryService groupInfoQueryService;

    public GroupInfoDto findGroupByGroupId(Long groupId) {
        return groupInfoQueryService.findGroupByGroupId(groupId);
    }

    /* 모임 등록 */
    @Transactional
    public void registGroup(GroupInfoDto newGroupInfo) {
        groupInfoRepository.save(modelMapper.map(newGroupInfo, GroupInfo.class));
    }

    /* 모임 삭제 */
    @Transactional
    public void deleteGroupInfo(Long groupId) {

        // 1. 모임에 달린 댓글 모두 삭제
        groupCommentRepository.deleteByGroupId(groupId);

        // 2. 모임에 가입된 사용자 삭제
        groupUserRepository.deleteByGroupId(groupId);

        // 3. 모임 삭제
        groupInfoRepository.deleteById(groupId);
    }

    /* 모임에 가입된 사용자 아이디 가져오는 메소드 */
    public List<Long> findGroupUserByGroupId(Long groupId) {
        return groupInfoQueryService.findGroupUserByGroupId(groupId);
    }

    /* 모임에 신규 사용자 가입 메소드 */
    public void registGroupUser(Long userId, Long groupId) {
        GroupUserDto newGroupUser = new GroupUserDto(null, userId, groupId);

        groupUserRepository.save(modelMapper.map(newGroupUser, GroupUser.class));
    }
}
