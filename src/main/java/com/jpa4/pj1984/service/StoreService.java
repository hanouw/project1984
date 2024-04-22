package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.PaymentMem;
import com.jpa4.pj1984.domain.ProductFile;
import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.domain.StoreStatus;
import com.jpa4.pj1984.dto.PageRequestDTO;
import com.jpa4.pj1984.dto.PaymentMemDTO;
import com.jpa4.pj1984.dto.StoreDTO;
import com.jpa4.pj1984.dto.StoreForm;
import com.jpa4.pj1984.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {



    private final StoreRepository storeRepository;
    private final FileUploadService fileUploadService;

    // 서점 목록 조회
    public List<StoreDTO> findStoreList(Long storeId, PageRequestDTO pageRequestDTO) {
        List<Store> storeEntityList = storeRepository.findStoreListByStoreId(storeId, pageRequestDTO);
        List<StoreDTO> list = new ArrayList<>();
        for (Store storeList : storeEntityList) {
            StoreDTO storeDTO = new StoreDTO(storeList);
            list.add(storeDTO);
        }
        return list;
    }

    // 주문관리 - 검색된 주문 개수 조회 판매자 ver
    public Long countStoreList(Long storeId, PageRequestDTO pageRequestDTO) {
        return storeRepository.countStoreListByStoreId(storeId, pageRequestDTO);
    }

    // 서점 상세 조회 (한개 조회)
    public Store getOneStore(Long storeId) {

        Store store = storeRepository.findById(storeId).orElse(null);

        /* Optional<Store> byId = storeRepository.findById(storeId);
        if (byId.isPresent()) { // null =false or true
            return new StoreDTO(byId);
        }*/
        return store;
    }
    
    // 서점 상세 수정
    public void updateOneBoard(StoreForm storeForm) throws IOException { // 사용자가 수정한 값이 StoreForm 으로 넘어옴
        Store findStore = storeRepository.findById(storeForm.getStoreId()).orElse(null);// DB에서 조회 (수정전상태)
        // 1. 새로등록한 이미지들이 있다면 이미지들의 파일을 서버쪽 폴더에 저장 -> FileUploadService --> 저장하고, 파일이름들저장한 ProductFile을 리턴해줌
        if(!storeForm.getNewStoreImage().isEmpty()) { // 썸네일
            ProductFile storeImage = fileUploadService.saveFile(storeForm.getNewStoreImage());
            findStore.setStoreImageOrigin(storeImage.getOrgFileName()); // 원본이름 entity에 setting -> 수정 처리
            findStore.setStoreImageStored(storeImage.getStoredFileName()); // 저장된 파일명 entity 에 setting -> 수정처리
        }
        if(!storeForm.getNewStoreImage01().isEmpty()) { // 이미지1
            ProductFile storeImage = fileUploadService.saveFile(storeForm.getNewStoreImage01());
            findStore.setStoreImageOrigin01(storeImage.getOrgFileName());
            findStore.setStoreImageStored01(storeImage.getStoredFileName());
        }
        if(!storeForm.getNewStoreImage02().isEmpty()) { // 이미지2
            ProductFile storeImage = fileUploadService.saveFile(storeForm.getNewStoreImage02());
            findStore.setStoreImageOrigin02(storeImage.getOrgFileName());
            findStore.setStoreImageStored02(storeImage.getStoredFileName());
        }
        if(!storeForm.getNewStoreImage03().isEmpty()) { // 이미지3
            ProductFile storeImage = fileUploadService.saveFile(storeForm.getNewStoreImage03());
            findStore.setStoreImageOrigin03(storeImage.getOrgFileName());
            findStore.setStoreImageStored03(storeImage.getStoredFileName());
        }

        // 2. DB에 저장
        findStore.setStoreText(storeForm.getStoreText());
        findStore.setStoreOneReview(storeForm.getStoreOneReview());
        findStore.setStoreReview(storeForm.getStoreReview());
        findStore.setStoreTag(storeForm.getStoreTag());
        findStore.setStoreAddress(storeForm.getStoreAddress());
        findStore.setStoreOperateTime(storeForm.getStoreOperateTime());
        findStore.setStoreAccount(storeForm.getStoreAccount());
        findStore.setStoreBankName(storeForm.getStoreBankName());
    }

    // VIEW 목록 조회용
    public List<StoreDTO> findStoreAllList(){
        List<Store> all = storeRepository.findAll();
        List<StoreDTO> storeDTOList = new ArrayList<>();
        for (Store list : all){
            storeDTOList.add(new StoreDTO(list));
        }
        return storeDTOList;
    }




}

/*
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Long save(BoardForm boardForm) {
        // boardForm 의 writer (로그인한 사용자 username)으로 회원 정보 찾아서 ㅓ
        // Member member = memberRepository.findByUsername(boardForm.getWriter()).orElse(null);
        Board entity = boardForm.toEntity(); // boardForm을 entity로 변경
        // entity.setMember(member); //  회원 정보 entity에 추가하고
        Board savedBoard = boardRepository.save(entity); // 저장
        return savedBoard.getId();
    }

    // 글 목록 조회 : 페이징 O
    public Page<Board> getListWithPaging(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("id").descending());

        Page<Board> result = boardRepository.findAll(pageable); // 결과, 1.글조회 query, 2.count query
        return result;
    }

    // 글 상세(한개 조회)
    public BoardDTO getOneBoard(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        board.setCount(board.getCount() + 1); // 조회수 올리기 dirty checking
        return new BoardDTO(board); // Entity -> BoardDTO로 변환해서 리턴
    }

    // 글 수정
    public void updateOneBoard(BoardForm boardForm) { // 사용자가 수정한 값이 boardForm으로 넘어옴
        Board findBoard = boardRepository.findById(boardForm.getId()).orElse(null); // DB에서 조회 (수정전상태)
        findBoard.setTitle(boardForm.getTitle());
        findBoard.setContent(boardForm.getContent()); // dirty checking
    }
}
*/


