package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.dto.StoreDTO;
import com.jpa4.pj1984.dto.StoreForm;
import com.jpa4.pj1984.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

//    public Store save(StoreForm storeForm, String storeId){
//
//        Store entity = storeForm.toEntity();
//        Store storeSaved = storeRepository.save(entity);
//        return storeSaved;
//    }

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
    public void updateOneBoard(StoreForm storeForm) { // 사용자가 수정한 값이 StoreForm 으로 넘어옴
        Store findStore = storeRepository.findById(storeForm.getStoreId()).orElse(null);// DB에서 조회 (수정전상태)

//        Store modifyStore = new Store(storeForm.getStoreImageName(),storeForm.getStoreImageId(),storeForm.getStoreTitle(),
//                storeForm.getStoreText(),storeForm.getStoreOneReview(),storeForm.getStoreReview(),storeForm.getStoreTag(),
//                storeForm.getStoreInsideImageName01(),storeForm.getStoreInsideImageId01(),storeForm.getStoreInsideImageName02(),
//                storeForm.getStoreInsideImageId02(),storeForm.getStoreInsideImageName03(),storeForm.getStoreInsideImageId03(),
//                storeForm.getStoreAddress(),storeForm.getStorePhoneNum(),storeForm.getStoreOperateTime(),storeForm.getStoreOwner(),
//                storeForm.getStoreBankName(),storeForm.getStoreAccount(),storeForm.getStoreCrn());
//        findStore.setStore(modifyStore);
        /*
        Board findBoard = boardRepository.findById(boardForm.getId()).orElse(null); // DB에서 조회 (수정전상태)
        findBoard.setTitle(boardForm.getTitle());
        findBoard.setContent(boardForm.getContent()); // dirty checking
        */
        // 회원 정보 수정
        /*
        public void updateMember(MemberUpdateDTO updateDTO) {
            Member findMember = memberRepository.findById(updateDTO.getId()).orElse(null);
            findMember.setEmail(updateDTO.getEmail());
            Address modifyAddr = new Address(updateDTO.getCity(), updateDTO.getStreet(), updateDTO.getZipcode());
            findMember.setAddress(modifyAddr);
        }
        */
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


