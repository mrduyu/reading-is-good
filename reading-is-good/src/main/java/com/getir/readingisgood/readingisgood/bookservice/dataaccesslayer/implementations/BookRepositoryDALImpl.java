package com.getir.readingisgood.readingisgood.bookservice.dataaccesslayer.implementations;


import com.getir.readingisgood.readingisgood.authenticationservice.responses.BaseResponse;
import com.getir.readingisgood.readingisgood.bookservice.dataaccesslayer.interfaces.BookRepositoryDAL;
import com.getir.readingisgood.readingisgood.bookservice.models.BookDAO;
import com.getir.readingisgood.readingisgood.bookservice.models.BookDTO;
import com.getir.readingisgood.readingisgood.bookservice.requests.InsertBookRequest;
import com.getir.readingisgood.readingisgood.bookservice.requests.UpdateBookRequest;
import com.getir.readingisgood.readingisgood.bookservice.responses.*;
import com.getir.readingisgood.readingisgood.errormessages.ErrorCodes;
import com.getir.readingisgood.readingisgood.orderservice.requests.OrderBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryDALImpl implements BookRepositoryDAL {
    @Autowired
    private MongoTemplate mongoTemplate;

    public InsertBookResponse insertBook(InsertBookRequest insertBookRequest) {
        Query query = new Query();
        query.addCriteria(Criteria.where("author").is(insertBookRequest.getAuthor()));
        List<BookDAO> bookDAOList = mongoTemplate.find(query, BookDAO.class);
        boolean sameBookExists = false;
        if (bookDAOList != null && bookDAOList.size()>0)
        {
            for (BookDAO item: bookDAOList) {
                if (item.getName().equals(insertBookRequest.getName())){
                    sameBookExists = true;
                    break;
                }
            }
        }
        if (sameBookExists) // Yazarin ayni kitabi mevcut
        {
            return new InsertBookResponse(ErrorCodes.ERR0008.name(),ErrorCodes.ERR0008.getDescription(), null);
        }
        else // Yazar veya yazarin ayni kitabi yok. Yeni kayit yarat.
        {
            BookDAO bookDAO = new BookDAO(insertBookRequest.getName(),insertBookRequest.getAuthor(),"A",insertBookRequest.getQuantity(),insertBookRequest.getAmountPerItem());
            BookDAO savedBook = mongoTemplate.save(bookDAO);
            return new InsertBookResponse(ErrorCodes.SUCCESSFUL.name(),null, savedBook.getId());
        }
    }
    @Override
    public UpdateBookResponse updateBook(UpdateBookRequest updateBookRequest) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(updateBookRequest.getId()));
        BookDAO bookData = mongoTemplate.findOne(query, BookDAO.class);
        if (bookData != null) // Yazarin ayni kitabi mevcut. Bu kaydi guncelle.
        {
            bookData.setQuantity(bookData.getQuantity()+updateBookRequest.getQuantity());
            bookData.setStatus("A"); // Active
            bookData.setAuthor(updateBookRequest.getAuthor());
            bookData.setName(updateBookRequest.getName());
            if (updateBookRequest.getAmountPerItem().compareTo(BigDecimal.ZERO) != 0)
                bookData.setAmountPerItem(updateBookRequest.getAmountPerItem());
            BookDAO savedBook = mongoTemplate.save(bookData);
            return new UpdateBookResponse(ErrorCodes.SUCCESSFUL.name(),null,savedBook.getId());
        }
        else // Yazar veya yazarin kitabi yok
        {
            return new UpdateBookResponse(ErrorCodes.ERR0007.name(),ErrorCodes.ERR0007.getDescription(), null);
        }
    }

    @Override
    public OrderBookResponse orderBook(OrderBookRequest updateBookRequest) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(updateBookRequest.getBookId()));
        BookDAO bookDAO = mongoTemplate.findOne(query,BookDAO.class);
        if (bookDAO != null){
            if (updateBookRequest.isRemovedFromBasket())
            {
                bookDAO.setQuantity(bookDAO.getQuantity() + updateBookRequest.getQuantity());
                bookDAO.setStatus("A"); // Active
            }
            else
            {
                if (bookDAO.getQuantity()<updateBookRequest.getQuantity() && bookDAO.getStatus().equals("A")) {
                    return new OrderBookResponse(ErrorCodes.ERR0002.name(),ErrorCodes.ERR0002.getDescription(), null);
                }
                bookDAO.setQuantity(bookDAO.getQuantity()-updateBookRequest.getQuantity());
                if (bookDAO.getStatus().equals("P")){
                    return new OrderBookResponse(ErrorCodes.ERR0005.name(),ErrorCodes.ERR0005.getDescription(), null);
                }
                if (bookDAO.getQuantity() == 0){
                    bookDAO.setStatus("P"); // Passive
                }
            }
            BookDAO savedBook = mongoTemplate.save(bookDAO);
            return new OrderBookResponse(ErrorCodes.SUCCESSFUL.name(),null, savedBook.getId());
        }
        return new OrderBookResponse(ErrorCodes.ERR0003.name(),ErrorCodes.ERR0003.getDescription(), null);
    }

    @Override
    public ListBooksResponse getAllBooks() {
        List<BookDAO> bookList = mongoTemplate.findAll(BookDAO.class);
        if (bookList != null && bookList.size()>0){
            List<BookDTO> bookDTOList = new ArrayList<>();
            for (BookDAO bookDao:bookList) {
                BookDTO bookDTO = new BookDTO(bookDao.getId(), bookDao.getName(), bookDao.getAuthor(),bookDao.getQuantity(),bookDao.getAmountPerItem());
                bookDTOList.add(bookDTO);
            }
            return new ListBooksResponse(ErrorCodes.SUCCESSFUL.name(),null,bookDTOList);
        }
        return new ListBooksResponse(ErrorCodes.ERR0009.name(),ErrorCodes.ERR0009.getDescription(),null);
    }

    @Override
    public BookDetailResponse getBookDetail(String bookId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(bookId));
        BookDAO bookDAO = mongoTemplate.findOne(query,BookDAO.class);
        if (bookDAO != null)
            return new BookDetailResponse(ErrorCodes.SUCCESSFUL.name(), null,new BookDTO(bookDAO.getId(),bookDAO.getName(),bookDAO.getAuthor(),bookDAO.getQuantity(),bookDAO.getAmountPerItem()));
        return new BookDetailResponse(ErrorCodes.ERR0007.name(), ErrorCodes.ERR0007.getDescription(),null);
    }
}
